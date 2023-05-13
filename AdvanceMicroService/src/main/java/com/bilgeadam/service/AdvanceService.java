package com.bilgeadam.service;

import com.bilgeadam.repository.IAdvanceRepository;
import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.utility.ServiceManager;
import org.springframework.stereotype.Service;

@Service
public class AdvanceService extends ServiceManager<Advance,String > {

    private final IAdvanceRepository advanceRepository;

    public AdvanceService(IAdvanceRepository advanceRepository) {
        super(advanceRepository);
        this.advanceRepository = advanceRepository;
    }
}
