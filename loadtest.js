import http from 'k6/http';
import { check } from 'k6';

export const options = {
    scenarios: {
        profile_load_test: {
            executor: 'constant-arrival-rate',
            rate: 100,                  // 100 Requests per second
            timeUnit: '1s',
            duration: '60s',           // Run for 60 sec
            preAllocatedVUs: 100,       // Initial VUs
            maxVUs: 100,               // Max scalability
        },
    },
};

export default function () {
    const url = 'http://localhost:8085/api/v1/create/profile';

    const firstNames = ["Arjun", "Rohan", "Neha", "Asha", "Vivek", "Sita", "Kiran", "Rita"];
    const lastNames = ["Sharma", "Verma", "Gupta", "Iyer", "Khan", "Patel", "Singh", "Das"];
    const cities = ["mumbai", "delhi", "noida", "bengaluru", "pune", "kolkata", "guwahati"];

    const payload = JSON.stringify({
        phone: `99999${Math.floor(Math.random() * 90000) + 10000}`,
        firstname: firstNames[Math.floor(Math.random() * firstNames.length)],
        lastname: lastNames[Math.floor(Math.random() * lastNames.length)],
        email: `user_${Math.random().toString(36).substring(2,8)}@gmail.com`,
        location: cities[Math.floor(Math.random() * cities.length)],
        aadhar: `${Math.floor(100000000000 + Math.random() * 900000000000)}` // 12-digit random
    });


    const params = {
        headers: {
            'Content-Type': 'application/json',
        },
    };

    const res = http.post(url, payload, params);

    check(res, {
        'status is 200/201': r => r.status === 200 || r.status === 201,
    });
}
