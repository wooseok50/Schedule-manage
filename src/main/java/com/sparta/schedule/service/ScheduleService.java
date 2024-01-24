package com.sparta.schedule.service;

import com.sparta.schedule.dto.ScheduleRequestDto;
import com.sparta.schedule.dto.ScheduleResponseDto;
import com.sparta.schedule.entity.Schedule;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleService(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleRequestDto requestDto) {
        // RequestDto -> Entity
        Schedule schedule = new Schedule(requestDto);

        // DB 저장
        scheduleRepository.save(schedule);

        ScheduleResponseDto scheduleResponseDto = new ScheduleResponseDto(schedule);

        return scheduleResponseDto;
    }

    // 일정 목록 전체 조회
    public List<ScheduleResponseDto> getSchedule() {
        // DB 조회
        return scheduleRepository.findAllByOrderByCreatedAtDesc().stream().map(ScheduleResponseDto::new).toList();
    }

    // 입력한 id로 선택한 일정 조회
    public ScheduleResponseDto getSelectedSchedule(Long id) {
        Schedule schedule = findSchedule(id);
        return new ScheduleResponseDto(schedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 schedule이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        if (schedule.getPassword() != null && schedule.getPassword().equals(requestDto.getPassword())) {
            // schedule 수정
            schedule.update(requestDto);
            return new ScheduleResponseDto(schedule);
        }
        throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
    }

    @Transactional
    public void deleteSchedule(Long id, ScheduleRequestDto requestDto) {
        // 해당 schedule이 DB에 존재하는지 확인
        Schedule schedule = findSchedule(id);

        if (!(schedule.getPassword() == null) && !schedule.getPassword().equals(requestDto.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        // schedule 삭제
        scheduleRepository.delete(schedule);
    }

    private Schedule findSchedule(Long id) {
        return scheduleRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 스케쥴은 존재하지 않습니다.")
        );
    }
}
