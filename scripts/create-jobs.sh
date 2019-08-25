#!/usr/bin/env bash
set -e -u

NUMBERS_OF_JOBS=$1
PORT=$2
BASE_URL=http://localhost:$PORT
echo "Creating ${NUMBERS_OF_JOBS} Jobs"

 for i in $(seq ${NUMBERS_OF_JOBS})
   do
     echo "launching job ${i}"
     COMPANY_ID=$(uuidgen)
     set -x
     curl -s $BASE_URL/$COMPANY_ID  -X PUT -H "Content-Type:application/json" -d '{"companyName":"test"}' >> curl.out
     curl -s $BASE_URL/ -X POST  -H "Content-Type:application/json" -d '{"companyName":"test"}' >> curl.out
     set +x
   done
