import easyocr

reader = easyocr.Reader(['en'])
result = reader.readtext('img.png')

for _, text, conf in result:
    print(text)

