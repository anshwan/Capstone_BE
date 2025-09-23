package com.example.capstone.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Tag(name = "Health API", description = "서버 상태 체크 API")
public class HealthController {

    @GetMapping("/health")
    @Operation(
            summary = "서버 상태 확인",
            description = "서버가 정상적으로 동작 중인지 확인합니다. `status=ok` 를 반환합니다.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "서버 정상 응답")
            }
    )
    public Map<String, Object> health() {
        return Map.of("status", "ok");
    }
}
