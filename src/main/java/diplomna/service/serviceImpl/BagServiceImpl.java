package diplomna.service.serviceImpl;

import diplomna.repository.BagRepository;
import diplomna.service.BagService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BagServiceImpl implements BagService {

    private final BagRepository bagRepository;
    private final ModelMapper modelMapper;

    public BagServiceImpl(BagRepository bagRepository, ModelMapper modelMapper) {
        this.bagRepository = bagRepository;
        this.modelMapper = modelMapper;
    }
}
