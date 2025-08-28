package pidev.eventconnect.services;

import pidev.eventconnect.entities.DiscountCode;

public interface IDiscountCodeService {

    public boolean existByCode (String code);

    public DiscountCode createCode (String emailParticipant, String firstNameParticipant,
                                    String lastNameParticipant,
                                    String discountCode);

    public void sendFreeAccess (String emailParticipant, String firstNameParticipant,
                                String lastNameParticipant);
}
