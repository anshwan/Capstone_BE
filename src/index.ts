import express from "express";
import cors from "cors";
import dotenv from "dotenv";
import cookieParser from "cookie-parser"; // ✅ 쿠키 사용 시 필요

dotenv.config();

import loginRouter from "./routes/login";
import modelRouter from "./routes/model";
import inferenceRouter from "./routes/inference"

const app = express();
const PORT = process.env.PORT || 8080;

// ✅ CORS 설정 (가장 위에 위치해야 함)
app.use(cors({
  origin: process.env.CLIENT_URL || "http://localhost:3000",
  credentials: true,
}));

// ✅ 쿠키 파서 및 JSON 파서
app.use(cookieParser());
app.use(express.json());

// ✅ 라우터 연결
app.use("/login", loginRouter);
app.use("/model", modelRouter);
app.use("/inference", inferenceRouter)

app.get("/", (req, res) => {
  res.send("Agent Chain Backend is running!");  
});

app.listen(PORT, () => {
  console.log(`🚀 Server running at http://localhost:${PORT}`);
});
