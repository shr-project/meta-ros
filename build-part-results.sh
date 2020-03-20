# no shebang, source this from other script

echo "sstate reuse stats:"
grep "^NOTE: .* sstate reuse.*scratch" log.*.${BUILD_DATE}.$$ | sort | uniq -c

echo "WARNING messages about generic license:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "No generic license file exists for: " | sort | uniq -c

echo "WARNING messages about license:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "The license listed .* was not in the licenses collected for recipe" | sort | uniq -c

echo "WARNING messages about version going backwards:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "\[version-going-backwards\]" | sort | uniq -c

echo "WARNING messages caused by sota:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "Android repo tool not found" | sort | uniq -c
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "Data in /media directory is not preserved by OSTree" | sort | uniq -c
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep "SOTA_PACKED_CREDENTIALS not set." | sort | uniq -c

echo "Other WARNING messages:"
grep "^WARNING:" log.*.${BUILD_DATE}.$$ | grep -v "No generic license file exists for: " | grep -v "The license listed .* was not in the licenses collected for recipe" | grep -v "Android repo tool not found" | grep -v "Data in /media directory is not preserved by OSTree" | grep -v "SOTA_PACKED_CREDENTIALS not set." | grep -v "\[version-going-backwards\]" | sort | uniq -c

echo "Other error messages:"
grep -i "error[: ]" log.*.${BUILD_DATE}.$$ | grep -v "ERROR:" | grep -v "Summary:" | sort | uniq -c

echo "ERROR messages:"
grep "^ERROR:" log.*.${BUILD_DATE}.$$ | sort | uniq -c

echo "Summary:"
grep "Summary:" log.*.${BUILD_DATE}.$$ | sort | uniq -c
