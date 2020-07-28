package diplomna.service.serviceImpl;

import diplomna.repository.BagRepository;
import diplomna.service.BagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BagServiceImpl implements BagService {

    @Autowired
    private final BagRepository bagRepository;
    private final ModelMapper modelMapper;

    public BagServiceImpl(BagRepository bagRepository, ModelMapper modelMapper) {
        this.bagRepository = bagRepository;
        this.modelMapper = modelMapper;
    }
}
