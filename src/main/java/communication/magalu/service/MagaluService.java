package communication.magalu.service;

import communication.magalu.dto.MagaluDto;
import communication.magalu.entity.Magalu;
import communication.magalu.repository.MagaluRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MagaluService {

    private final MagaluRepository magaluRepository;

    public MagaluService(MagaluRepository magaluRepository) {
        this.magaluRepository = magaluRepository;
    }

    @Transactional
    public MagaluDto scheduleMagalu(MagaluDto dto) {
        Magalu magalu = new Magalu();
        magalu.setScheduledTime(LocalDateTime.now());
        magalu.setRecipient(dto.getRecipient());
        magalu.setMessage(dto.getMessage());
        magalu.setType(dto.getType());
        magalu.setStatus("SCHEDULED");

        magalu = magaluRepository.save(magalu);

        return toDto(magalu);
    }

    public MagaluDto getMagalu(Long id) {
        Magalu magalu = magaluRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Magalu not found"));
        return toDto(magalu);
    }

    public List<MagaluDto> getAllMagalu() {
        return magaluRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void deleteMagalu(Long id) {
        if (!magaluRepository.existsById(id)) {
            throw new IllegalArgumentException("Invalid ID");
        }
        magaluRepository.deleteById(id);
    }

    private MagaluDto toDto(Magalu magalu) {
        MagaluDto dto = new MagaluDto();
        dto.setRecipient(magalu.getRecipient());
        dto.setMessage(magalu.getMessage());
        dto.setType(magalu.getType());
        return dto;
    }
}