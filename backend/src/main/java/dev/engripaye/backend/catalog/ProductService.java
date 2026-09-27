package dev.engripaye.backend.catalog;

import dev.engripaye.backend.business.BusinessRepository;
import dev.engripaye.backend.business.TenantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final BusinessRepository businessRepository;
    private final TenantService tenantService;
}
