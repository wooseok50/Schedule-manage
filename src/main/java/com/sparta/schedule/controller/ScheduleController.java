package com.sparta.schedule.controller;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.service.CommonResponse;
import com.sparta.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping()
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> createSchedule(@RequestBody ScheduleRequestDto requestDto) {
        return ResponseEntity.ok()
                .body(CommonResponse.<ScheduleResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("일정 생성이 완료 되었습니다.")
                        .data(scheduleService.createSchedule(requestDto))
                        .build());
    }

    @GetMapping()
    public ResponseEntity<CommonResponse<List<ScheduleResponseDto>>> getSchedule() {
        return ResponseEntity.ok()
                .body(CommonResponse.<List<ScheduleResponseDto>>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("모든 일정 조회가 완료 되었습니다.")
                        .data(scheduleService.getSchedule())
                        .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> getSelectedSchedule(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(CommonResponse.<ScheduleResponseDto>builder()
                        .statusCode(HttpStatus.OK.value())
                        .msg("선택한 일정 조회가 완료 되었습니다.")
                        .data(scheduleService.getSelectedSchedule(id))
                        .build());
    }


    @PutMapping("/{id}")
    public ResponseEntity<CommonResponse<ScheduleResponseDto>> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        CommonResponse<ScheduleResponseDto> response;

        try {
            ScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, requestDto);

            response = CommonResponse.<ScheduleResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("선택한 일정이 수정되었습니다.")
                    .data(updatedSchedule)
                    .build();

        } catch (IllegalArgumentException e) {
            response = CommonResponse.<ScheduleResponseDto>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .msg(e.getMessage())
                    .build();
        }
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CommonResponse> deleteSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {
        CommonResponse<ScheduleResponseDto> response;

        try {
            scheduleService.deleteSchedule(id, requestDto);

            response = CommonResponse.<ScheduleResponseDto>builder()
                    .statusCode(HttpStatus.OK.value())
                    .msg("선택한 일정이 삭제되었습니다.")
                    .build();

        } catch (IllegalArgumentException e) {

            response = CommonResponse.<ScheduleResponseDto>builder()
                    .statusCode(HttpStatus.BAD_REQUEST.value())
                    .msg(e.getMessage())
                    .build();
        }
        return ResponseEntity.ok().body(response);
    }
}
