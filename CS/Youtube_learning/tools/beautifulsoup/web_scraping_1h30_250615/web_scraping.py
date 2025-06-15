
from bs4 import BeautifulSoup
import requests
import re # regular expression

with open("index.html", "r") as f:
    doc = BeautifulSoup(f, "html.parser")

#print(doc.prettify()) //give the filw with indentations

tag = doc.title

#tag.string = "Hello"

tags = doc.fina_all("p")


#tags = doc.find_all("p")[0]

#print(tags.find_all("b")) nested tags

print(tag.string)

url = "https://www.newegg.ca/gigabyte-geforce-rtx-3080-ti-gv-n308tgaming-oc-12gd/p/N82E16814932436?Description=3080&cm_re=3080-_-14-932-436-_-Product"

results = requests.get(url)
doc = BeautifulSoup(results.text, "html.parser")
print(doc.prettify())


prices = doc.find_all(text = "$")
# parent = prices[0].parent
# strong = parent.find("strong") only want the value 

print(prices)

# tag = doc.find("option")(["p","div"]) (["option"], text = "Undergraduate") #find the tags with certain content
# tag["value"]= "new value" # change attribute

#tags = doc.fina_all(text= re.compile("\$.*"), limit = 1)
for tag in tags:
	print(tag.strip())

tags = doc.find_all("input", type="text") #(class_ ="btn-item") find the matched class attribute
for tag in tags:
	tag['placeholder'] = "I changed you!"

with open("changed.html", "w") as file:
	file.write(str(doc))
	


url = "https://coinmarketcap.com/"
result = requests.get(url).text
doc = BeautifulSoup(result, "html.parser")

tbody = doc.tbody
trs = tbody.contents
#tr = trs[0].next_sibling  next_sibiligs previous_sibling # next row
# descendamts #inside or come after this tag

prices = {}

for tr in trs[:10]:
	name, price = tr.contents[2:4]
	fixed_name = name.p.string
	fixed_price = price.a.string

	prices[fixed_name] = fixed_price

print(prices)



search_term = input("What product do you want to search for? ")

url = f"https://www.newegg.ca/p/pl?d={search_term}&N=4131"
page = requests.get(url).text
doc = BeautifulSoup(page, "html.parser")

page_text = doc.find(class_="list-tool-pagination-text").strong
pages = int(str(page_text).split("/")[-2].split(">")[-1][:-1])

items_found = {}

for page in range(1, pages + 1):
	url = f"https://www.newegg.ca/p/pl?d={search_term}&N=4131&page={page}"
	page = requests.get(url).text
	doc = BeautifulSoup(page, "html.parser")

	div = doc.find(class_="item-cells-wrap border-cells items-grid-view four-cells expulsion-one-cell")
	items = div.find_all(text=re.compile(search_term))

	for item in items:
		parent = item.parent
		if parent.name != "a":
			continue

		link = parent['href']
		next_parent = item.find_parent(class_="item-container")
		try:
			price = next_parent.find(class_="price-current").find("strong").string
			items_found[item] = {"price": int(price.replace(",", "")), "link": link}
		except:
			pass

sorted_items = sorted(items_found.items(), key=lambda x: x[1]['price'])

for item in sorted_items:
	print(item[0])
	print(f"${item[1]['price']}")
	print(item[1]['link'])
	print("-------------------------------")
	
[("3080 FTW", {"price": 2999, "link": "www."})]
