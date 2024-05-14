package com.mylibrary.application.rent.get;

import com.mylibrary.application.UseCase;
import com.mylibrary.domain.rent.RentGateway;

public class GetRentUseCase extends UseCase<GetRentInput, GetRentOutput> {
    private final RentGateway rentGateway;

    public GetRentUseCase(RentGateway rentGateway) {
        this.rentGateway = rentGateway;
    }

    @Override
    public GetRentOutput execute(GetRentInput input) {
        var rent = this.rentGateway.findById(input.rentId());
        return GetRentOutput.from(rent);
    }
}
