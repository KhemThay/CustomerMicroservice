package dtwg.mptc.ecommerce.service;

import dtwg.mptc.ecommerce.domain.valueobject.Email;
import dtwg.mptc.ecommerce.domain.valueobject.PhoneNumber;
import dtwg.mptc.ecommerce.entity.Customer;
import dtwg.mptc.ecommerce.event.CustomerCreatedEvent;
import dtwg.mptc.ecommerce.event.CustomerDeactivatedEvent;
import dtwg.mptc.ecommerce.event.CustomerUpdatedEvent;


public interface CustomerDomainService {
    CustomerCreatedEvent validateAndInitiateCustomer(Customer customer);

    CustomerUpdatedEvent updateCustomer(Customer customer, String familyName, String givenName,
                                        Email email, PhoneNumber phoneNumber);

    CustomerDeactivatedEvent deactivateCustomer(Customer customer);
}
