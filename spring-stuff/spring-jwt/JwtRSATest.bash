TOKEN=$(curl -X POST "http://localhost:8080/loginRSA?username=testUser")
echo "$TOKEN"

RESPONSE=$(curl -s -H "Authorization: Bearer $TOKEN" "http://localhost:8080/secure-dataRSA")
echo "$RESPONSE"