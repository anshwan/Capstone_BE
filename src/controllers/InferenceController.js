"use strict";
var __awaiter = (this && this.__awaiter) || function (thisArg, _arguments, P, generator) {
    function adopt(value) { return value instanceof P ? value : new P(function (resolve) { resolve(value); }); }
    return new (P || (P = Promise))(function (resolve, reject) {
        function fulfilled(value) { try { step(generator.next(value)); } catch (e) { reject(e); } }
        function rejected(value) { try { step(generator["throw"](value)); } catch (e) { reject(e); } }
        function step(result) { result.done ? resolve(result.value) : adopt(result.value).then(fulfilled, rejected); }
        step((generator = generator.apply(thisArg, _arguments || [])).next());
    });
};
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
exports.RequestInference = void 0;
const prisma_1 = require("../generated/prisma");
const axios_1 = __importDefault(require("axios"));
const prisma = new prisma_1.PrismaClient();
const RequestInference = (req, res) => __awaiter(void 0, void 0, void 0, function* () {
    var _a;
    console.log("요청 도착");
    console.log("req.user:", req.user);
    const wallet = (_a = req.user) === null || _a === void 0 ? void 0 : _a.wallet;
    const { model_name, prompt } = req.body;
    if (!wallet || !prompt) {
        res.status(400).json({ message: "prompt를 입력하세요." });
        return;
    }
    try {
        const baseUrl = process.env.CHEETAH_SERVER_URL;
        const aiRequestBody = {
            prompt: prompt
        };
        console.log(aiRequestBody);
        const fixedModelName = "meta-llamaLlama-3.2-3B";
        const aiResponse = yield axios_1.default.post(`${baseUrl}?model_name=${encodeURIComponent(fixedModelName)}`, aiRequestBody);
        const generatedText = aiResponse.data.generated_text;
        res.status(200).json({ generate_text: generatedText });
    }
    catch (error) {
        console.error("AI 추론 실패:", error);
        res.status(500).json({ message: "AI 서버 요청 실패" });
    }
});
exports.RequestInference = RequestInference;
