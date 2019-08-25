#!/usr/bin/env bash
set -e
NUMBERS_OF_JOBS=${1:-100}


echo "Creating ${NUMBERS_OF_JOBS} Jobs"

 for i in $(seq ${NUMBERS_OF_JOBS})
   do
     echo "launching job ${i}"
     COMPANY_ID=$(uuidgen)
     set -x
     curl -s http://localhost:2000/$COMPANY_ID  -X PUT -H "Content-Type:application/json" -d '{"companyName":"test"}' >> curl.out
     curl -s http://localhost:2000/  -X POST -H "Content-Type:application/json" -d '{"companyName":"test"}' >> curl.out
     set +x
   done