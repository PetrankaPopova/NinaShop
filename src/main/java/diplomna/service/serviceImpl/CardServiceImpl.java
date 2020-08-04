package diplomna.service.serviceImpl;

import diplomna.repository.BagRepository;
import diplomna.service.CardService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private final BagRepository bagRepository;
    private final ModelMapper modelMapper;

    public CardServiceImpl(BagRepository bagRepository, ModelMapper modelMapper) {
        this.bagRepository = bagRepository;
        this.modelMapper = modelMapper;
    }
}
