TOKEN=$(curl -X POST "http://localhost:8080/login?username=testUser")
echo "$TOKEN"

RESPONSE=$(curl -s -H "Authorization: Bearer $TOKEN" "http://localhost:8080/secure-data")
echo "$RESPONSE"