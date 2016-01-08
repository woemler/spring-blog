#!/usr/bin/env bash
read -p "Enter desired username: " user
if [ -z "$user" ]; then
    echo "Username must not be empty!"
    break;
fi
read -s -p "Enter password: " pw
if [ -z "$pw" ]; then
    echo "Password must not be empty!"
    break;
fi
echo ""
read -s -p "Enter password again : " pw2
if [ "$pw" != "$pw2" ]; then
    echo "Passwords do not match!"
    break;
fi
echo ""
read -p "Enter email: " email
if [ -z "$email" ]; then
    echo "Email must not be empty!"
    break;
fi
mvn -e -q -f pom.xml exec:java -Dexec.mainClass="me.woemler.springblog.security.CreateAdminUser" -Dexec.args="$user $pw $email"
