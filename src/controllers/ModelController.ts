// ✅ backend/controllers/modelController.ts
import { Request, Response } from "express";
import { uploadToS3 } from "../services/s3Service";
import { createInitializeTx } from "../services/solanaService";
import { AuthRequest } from "../middlewares/authMiddleware";
import { PrismaClient } from "@prisma/client";

const prisma = new PrismaClient();

// ✅ 1. 모델 파일 업로드
export const handleModelUpload = async (req: AuthRequest, res: Response): Promise<void> => {
  const wallet = req.user?.wallet;
  const files = req.files as Express.Multer.File[];
  const { modelName, description, isDerivative, royalty } = req.body;

  if (!wallet || !files || files.length === 0 || !modelName || !description || !isDerivative || !royalty) {
    res.status(400).json({ error: "필수 정보 누락" });
    return;
  }

  try {
    const uploadedFiles = [];
    const safeModelName = modelName.replace(/[^a-zA-Z0-9-_]/g, "_");
    const folderKey = `models/${safeModelName}/`;

    for (const file of files) {
      const relativePath = file.originalname.replace(/\\/g, "/");
      const s3Key = `models/${safeModelName}/${relativePath}`;

      const { s3_key, url } = await uploadToS3(file.path, s3Key);
      uploadedFiles.push({ s3_key, url, originalName: file.originalname });
    }

    res.status(200).json({
      message: "모델 폴더 업로드 성공",
      uploadedCount: uploadedFiles.length,
      files: uploadedFiles,
      s3_key: folderKey,
    });
  } catch (error) {
    console.error("모델 업로드 실패:", error);
    res.status(500).json({ error: "모델 업로드 실패" });
  }
};

// ✅ 2. 트랜잭션 생성
export const handleTransactionInit = async (req: AuthRequest, res: Response): Promise<void> => {
  const wallet = req.user?.wallet;
  const { s3_key, royalty, is_derivative } = req.body;

  if (!wallet || !s3_key || royalty === undefined || is_derivative === undefined) {
    res.status(400).json({ error: "필수 파라미터 누락" });
    return;
  }

  try {
    const txBase64 = await createInitializeTx(wallet, s3_key, royalty, is_derivative);
    res.status(200).json({ transaction: txBase64 });
  } catch (error) {
    console.error("트랜잭션 생성 실패:", error);
    res.status(500).json({ error: "트랜잭션 생성 실패" });
  }
};

// ✅ 3. 트랜잭션 이후 모델 정보 DB 저장
export const handleModelComplete = async (req: AuthRequest, res: Response): Promise<void> => {
  const wallet = req.user?.wallet;
  const { modelName, description, is_derivative, royalty, s3_key } = req.body;

  if (!wallet || !modelName || !description || !royalty || !s3_key || is_derivative === undefined) {
    res.status(400).json({ error: "필수 항목 누락" });
    return;
  }

  try {
    await prisma.model.create({
      data: {
        model_name: modelName,
        description,
        is_derivative: is_derivative === true || is_derivative === "true",
        royalty: parseInt(royalty, 10),
        wallet_address: wallet,
        created_at: new Date(),
      },
    });

    console.log("✅ DB에 모델 저장 완료:", modelName);
    res.status(200).json({ message: "모델 DB 저장 성공" });
  } catch (error) {
    console.error("❌ 모델 DB 저장 실패:", error);
    res.status(500).json({ error: "모델 DB 저장 실패" });
  }
};

export const getAllModellist = async (req: AuthRequest, res: Response): Promise<void> => {
  try {
    const models = await prisma.model.findMany({
      orderBy: { created_at: "desc" },
    });
    res.status(200).json(models);
  } catch (error) {
    console.error("모델 목록 조회 실패:", error);
    res.status(500).json({ error: "모델 목록 조회 실패" });
  }
};

export const buyLicense = async (req: AuthRequest, res: Response): Promise<void> => {
  const wallet = req.user?.wallet;
  const { modelId } = req.body;

  if (!wallet || !modelId) {
    res.status(400).json({ message: "wallet 또는 modelId가 없습니다." });
    return;
  }

  try {
    const user = await prisma.user.findUnique({
      where : { wallet_address : wallet },
    });

    if (!user) {
      res.status(404).json({ message: "지갑 주소에 해당하는 사용자를 찾을 수 없습니다." });
      return;
    }
    
    // 라이선스 등록
    await prisma.license.create({
      data: {
        userId: user.id,
        modelId
      },
    });

    res.status(201).json({ message: "라이선스 구매 성공" });
  } catch (error: any) {
    if (error.code === "P2002") {
      res.status(400).json({ message : "이미 구매한 모델입니다." })
    } else {
      console.error("❌ 라이선스 구매 에러:", error);
      res.status(500).json({ message: "서버 오류로 구매에 실패했습니다." });
    }
  }
}

export const getOwnedModels = async (req: AuthRequest, res: Response): Promise<void> => {
  const wallet = req.user?.wallet;
  if (!wallet) {
    res.status(401).json({ message: "지갑 인증이 필요합니다." });
    return;
  }

  const user = await prisma.user.findUnique({
    where: { wallet_address: wallet },
  });

  if (!user) {
    res.status(404).json({ message: "사용자를 찾을 수 없습니다." });
    return;
  }

  const licenses = await prisma.license.findMany({
    where: { userId: user.id },
    include: { model: true },
  });

  const models = licenses.map((l: { model: { id: number; model_name: string } }) => ({
    id: l.model.id,
    model_name: l.model.model_name,
  }));

  res.status(200).json(models);
};