# scala-cli-bazel-crypto-des-password-salt

## Description
A demo for DES encryption of a password.
DES is a 48 byte encryption. SALT is the
mixing of the original text with a secret key.
It is considered more secure to store the
SALT then the encrypted original text, however
this can still be cracked with rainbow tables.

## Tech stack
- scala
- bazel

## Docker stack
- l.gcr.io/google/bazel:latest

## To run
`sudo ./install.sh -u`

## To stop (optional)
`sudo ./install.sh -d`

## For help
`sudo ./install.sh -h`

## Credits
- https://www.javatpoint.com/java-code-for-des
- https://gist.githubusercontent.com/ufologist/5581496/raw/243b922d8d75716bbe018cbbd716ee96b6e7e038/CryptString.java
- https://self-learning-java-tutorial.blogspot.com/2015/07/convert-secret-key-into-string-in-java.html
