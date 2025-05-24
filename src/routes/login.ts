import express from "express";
import { getNonce, verifySignature, refreshAccessToken } from "../controllers/loginController";

const router = express.Router();

router.get("/nonce", getNonce); 
router.post("/verify", verifySignature);
router.post("/refresh", refreshAccessToken);

export default router;
