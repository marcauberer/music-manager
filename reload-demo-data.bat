@echo off

echo "loading demo data ..."
rmdir /s /q .\data
mkdir data
copy .\0-mm-utils\src\main\resources\* .\data\
echo "done."