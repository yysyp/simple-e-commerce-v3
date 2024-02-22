set "simple-e-commerce-v3=..\doc3\3-learn\33-coding\337-mytool-demo-snippet-code\simple-e-commerce-v3"
cd ..
rd /S /Q %simple-e-commerce-v3%\
robocopy simple-e-commerce-v3 "%simple-e-commerce-v3%" /E /MIR /Z /XD "not-copy" "upload-folder" "log" ".git" ".gitattributes" ".mvn" "node_modules" "dist" ".umi" ".umi-production"
echo 'Copy current simple-e-commerce-v3 to doc3\3-learn\33-coding\337-mytool-demo-snippet-code\simple-e-commerce-v3 done!'
