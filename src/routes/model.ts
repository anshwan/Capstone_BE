// routes/modelRoutes.ts
import express from "express";
import multer from "multer";
import { handleModelUpload, handleTransactionInit, handleModelComplete, getAllModellist, buyLicense, getOwnedModels } from "../controllers/ModelController";
import { authenticateJWT } from "../middlewares/authMiddleware";

const router = express.Router();
const upload = multer({ dest: "uploads/" });

router.post("/upload", authenticateJWT, upload.array("files"), handleModelUpload);
router.post("/transaction", authenticateJWT, handleTransactionInit);
router.post("/complete", authenticateJWT, handleModelComplete);
router.get("/list", authenticateJWT, getAllModellist);
router.post("/buy", authenticateJWT, buyLicense);
router.get("/owned", authenticateJWT, getOwnedModels);

export default router;
