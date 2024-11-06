from util import key_mapper

def reading_data(fileName):
    with open(f"{fileName}.txt", 'r', encoding='utf-8') as file:
        data = file.read()
    
    data_list = data.splitlines()  
    return data_list[0], data_list[1:]


def data_processing(w, data): 
    data_map = {}

    for row in data: 
        row = row.replace(" ", "")
        key = key_mapper(row[1])
        data_map[key] = row[3:]

    w = w.split(':=')[1].strip()
    
    return w, data_map