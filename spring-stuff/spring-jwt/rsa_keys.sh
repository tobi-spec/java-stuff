#!/bin/bash

set -e

# 📁 Output directory
OUT_DIR=jwt-keys
mkdir -p "$OUT_DIR"

# 🔐 Generate 2048-bit RSA private key in PEM format
echo "Generating private key..."
openssl genpkey -algorithm RSA -out "$OUT_DIR/private.pem" -pkeyopt rsa_keygen_bits:2048

# 📤 Extract public key from private key (PEM format)
echo "Extracting public key..."
openssl rsa -in "$OUT_DIR/private.pem" -pubout -out "$OUT_DIR/public.pem"

# 🔄 Convert private key to DER (PKCS#8 format)
echo "Converting private key to DER..."
openssl pkcs8 -topk8 -inform PEM -outform DER -in "$OUT_DIR/private.pem" -nocrypt -out "$OUT_DIR/private.der"

# 🔄 Convert public key to DER
echo "Converting public key to DER..."
openssl rsa -in "$OUT_DIR/private.pem" -pubout -outform DER -out "$OUT_DIR/public.der"

# 🔡 Base64 encode both keys (suitable for application.yml)
echo "Encoding keys to Base64..."
base64 "$OUT_DIR/private.der" > "$OUT_DIR/private.der.b64"
base64 "$OUT_DIR/public.der" > "$OUT_DIR/public.der.b64"

echo ""
echo "✅ RSA key pair generated and converted!"
echo "👉 Use the following values in your application.yml:"
echo ""

echo "jwt:"
echo "  rsa:"
echo "    private-key: |"
sed 's/^/      /' "$OUT_DIR/private.der.b64"

echo ""
echo "    public-key: |"
sed 's/^/      /' "$OUT_DIR/public.der.b64"

echo ""
echo "📝 Place these under the correct indentation in your Spring Boot application.yml"
