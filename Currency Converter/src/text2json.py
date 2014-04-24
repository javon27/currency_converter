import re
import json

infile = open('currencylist.txt', 'rU')
outfile = open('currencylist.json', 'w')
units = []

for line in infile:
  match = re.search(r'(\w\w\w)\s\-\s([\w\s]+)\n',line)
  if match:  
    #print match.group(1), match.group(2)
    units.append({'CountryCurrency':match.group(2), 'Units':match.group(1)})
    
outfile.write(json.dumps({'units':units}, sort_keys=True, indent=4,
                         separators=(',',': ')))

infile.close()
outfile.close()
