# performance-testing

A
docker run --cpus="1" --memory="256m" --rm -d -p 8080:8080 performance-testing-quarkus-jvm
docker run --cpus="1" --memory="256m" --rm -d -p 8080:8080 performance-testing-quarkus-native

B
docker run --cpus="4" --memory="256m" --rm -d -p 8080:8080 performance-testing-quarkus-jvm
docker run --cpus="4" --memory="256m" --rm -d -p 8080:8080 performance-testing-quarkus-native

C
docker run --cpus="1" --memory="1g" --rm -d -p 8080:8080 performance-testing-quarkus-jvm
docker run --cpus="1" --memory="1g" --rm -d -p 8080:8080 performance-testing-quarkus-native

D
docker run --cpus="4" --memory="1g" --rm -d -p 8080:8080 performance-testing-quarkus-jvm
docker run --cpus="4" --memory="1g" --rm -d -p 8080:8080 performance-testing-quarkus-native


Slow 的定義為超出平均時間 2 個標準差


jvm - A
Success: 10240 Avg: 43.3607421875 Stdev: 52.18152316983758
Slow: 351 Avg: 226.2735042735 Stdev: 91.67968685420453

jvm - B
Success: 10240 Avg: 19.8083984375 Stdev: 26.47875991814699
Slow: 228 Avg: 147.4035087719 Stdev: 80.97829363784916

jvm - C
Success: 10240 Avg: 44.56181640625 Stdev: 50.27694652338563
Slow: 309 Avg: 228.5631067961 Stdev: 85.78544701863252

jvm - D
Success: 10240 Avg: 21.348828125 Stdev: 29.735806429223537
Slow: 241 Avg: 157.4273858921 Stdev: 93.04400794008515



native - A
Success: 10240 Avg: 17.56533203125 Stdev: 17.835479686981078
Slow: 597 Avg: 69.2562814070 Stdev: 17.777039568311007

native - B
Success: 10240 Avg: 12.0619140625 Stdev: 14.417554581610796
Slow: 177 Avg: 108.2598870057 Stdev: 27.617522146865653

native - C
Success: 10240 Avg: 17.73125 Stdev: 18.977542616012393
Slow: 658 Avg: 73.5471124620 Stdev: 18.164102266061196

native - D
Success: 10240 Avg: 10.694140625 Stdev: 9.051523409167423
Slow: 134 Avg: 66.8582089552 Stdev: 37.21309353712246

