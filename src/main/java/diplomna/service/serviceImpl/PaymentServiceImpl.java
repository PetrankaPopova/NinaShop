package diplomna.service.serviceImpl;

import diplomna.repository.PaymentRepository;
import diplomna.service.PaymentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final ModelMapper modelMapper;

    public PaymentServiceImpl(PaymentRepository paymentRepository, ModelMapper modelMapper) {
        this.paymentRepository = paymentRepository;
        this.modelMapper = modelMapper;
    }
}
