package communication.magalu.controller;

import communication.magalu.dto.MagaluDto;
import communication.magalu.service.MagaluService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/magalu")
public class MagaluController {

    private final MagaluService magaluService;

    public MagaluController(MagaluService magaluService) {
        this.magaluService = magaluService;
    }

    @PostMapping
    public ResponseEntity<MagaluDto> scheduleMagalu(@Valid @RequestBody MagaluDto dto) {
        MagaluDto createdMagalu = magaluService.scheduleMagalu(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMagalu);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MagaluDto> getMagalu(@PathVariable Long id) {
        MagaluDto magalu = magaluService.getMagalu(id);
        return ResponseEntity.ok(magalu);
    }

    @GetMapping
    public ResponseEntity<List<MagaluDto>> getAllMagalu() {
        List<MagaluDto> allMagalu = magaluService.getAllMagalu();
        return ResponseEntity.ok(allMagalu);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMagalu(@PathVariable Long id) {
        magaluService.deleteMagalu(id);
        return ResponseEntity.noContent().build();
    }
}
