package com.pedrolsoares.jewelry.service;

import com.pedrolsoares.jewelry.model.Jewel;
import com.pedrolsoares.jewelry.repository.JewelRepository;
import lombok.AllArgsConstructor;
import org.hibernate.PropertyNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JewelService {

    private final JewelRepository jewelRepository;

    public List<Jewel> listAll() {
        return jewelRepository.findAll();
    }

    public Jewel saveJewel(Jewel jewel) {
        return jewelRepository.save(jewel);
    }

    public Jewel removeJewel(Long id) {
        Optional<Jewel> jewelToDelete = jewelRepository.findById(id);

        if (jewelToDelete.isEmpty()) {
            throw new PropertyNotFoundException("Jewel not found");
        }

        jewelRepository.delete(jewelToDelete.get());

        return jewelToDelete.get();
    }


}
