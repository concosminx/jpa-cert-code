package com.nimsoc.jpa.entities.generators;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.security.*;
import java.util.Base64;
import java.util.UUID;

public class UUIDGenerator implements IdentifierGenerator {

  @Override
  public Object generate(SharedSessionContractImplementor session, Object object) {
    //return sign(UUID.randomUUID().toString());
    return UUID.randomUUID().toString();
  }

  private String sign(String primaryKeyValue) {
    try {
      KeyPairGenerator rsa = KeyPairGenerator.getInstance("RSA");
      rsa.initialize(2048);
      KeyPair keyPair = rsa.generateKeyPair();
      PrivateKey privateKey = keyPair.getPrivate();

      Signature signature = Signature.getInstance("SHA256withRSA");
      signature.initSign(privateKey);
      signature.update(primaryKeyValue.getBytes());
      byte[] result = signature.sign();

      return primaryKeyValue + "." + Base64.getEncoder().encodeToString(result);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
