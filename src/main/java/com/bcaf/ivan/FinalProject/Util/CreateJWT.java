package com.bcaf.ivan.FinalProject.Util;

import com.bcaf.ivan.FinalProject.Entity.User;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CreateJWT {
    public String buildJWT9(User user, String agencyId){

        //The JWT signature algoritma we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our Apikey Secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary("e8d867c35985a29d510819982bd7ba85b1f46a6853c95b9b8d80048d3c577186\n" +
                "73da3873f9c84a9a4acf881e742e1f18fce66862a7c3657238e729ff5f6bd399");
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map dataUser = new HashMap<String, Object>();
        dataUser.put("userId", user.getId());
        dataUser.put("name", user.getFirstName() + " " + user.getLastName());
        dataUser.put("email", user.getEmail());
        dataUser.put("agencyId", agencyId);

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setClaims(dataUser)
                .setIssuedAt(now)
//                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //If it has been specified, let's add the expiration
//        if (ttlMills >= 0){
//            long expMillis = nowMillis + ttlMills;
//            Date exp = new Date(expMillis);
//            builder.setExpiration(exp);
//        }
        long expMillis = nowMillis + 31536000000L; // 1 Year Expired
        Date exp = new Date(expMillis);
        builder.setExpiration(exp);

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }
}
