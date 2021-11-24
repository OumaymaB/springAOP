package enset.ma.service;

import enset.ma.aspects.Log;
import enset.ma.aspects.SecuredByAspect;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MetierImpl implements IMetier {
    @Override
    @Log
    @SecuredByAspect(roles={"USER", "ADMIN"})
    public void process() {
        System.out.println("Business Processing ...");
    }

    @Override
    @SecuredByAspect(roles="ADMIN")
    public double compute() {
        double data = 80.23;
        System.out.println("Business computing and returning result ...");
        return data;
    }
}
