package diplomna.service.serviceImpl;

import diplomna.repository.AboutRepository;
import diplomna.service.AboutService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AboutServiceImpl implements AboutService {

    @Autowired
    private final AboutRepository aboutRepository;
    private final ModelMapper modelMapper;

    public AboutServiceImpl(AboutRepository aboutRepository, ModelMapper modelMapper) {
        this.aboutRepository = aboutRepository;
        this.modelMapper = modelMapper;
    }
}
