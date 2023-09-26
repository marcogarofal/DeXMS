package org.zefxis.dexms.dex.protocols.https;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

class TrustAllCertificates implements X509TrustManager {
    public X509Certificate[] getAcceptedIssuers() {
        return null;
    }
    public void checkClientTrusted(X509Certificate[] certs, String authType) {
    }

    public void checkServerTrusted(X509Certificate[] certs, String authType) throws CertificateException {
        // Trust all server certificates, even if they are self-signed or expired
        for (X509Certificate cert : certs) {
                cert.checkValidity();
        }
    }
}