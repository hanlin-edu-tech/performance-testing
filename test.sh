#! bash

echo -e "*** Performance Testing ***\n" > report.txt

for image in quarkus-jvm quarkus-native nodejs-express nodejs-fastify
do
    echo -e "\n====== ${image} ======\n" >> report.txt
    for cpu in 1 4
    do
        for mem in 256m 1g
        do
            id=`docker run --cpus="${cpu}" --memory="${mem}" --rm -d -p 8080:8080 performance-testing-${image}`
            echo "=== ${cpu} CPUs and ${mem} memory ===" >> report.txt
            groovy tester/tester.groovy >> report.txt 2>&1
            docker stop ${id}
            echo "" >> report.txt
        done
    done
done