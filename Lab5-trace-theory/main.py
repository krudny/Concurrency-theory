def reading_data(fileName):
    with open(f"{fileName}.txt", 'r', encoding='utf-8') as file:
        data = file.read()
    
    data_list = data.splitlines()  
    return data_list

def key_mapper(letter): 
    return ord(letter) - 97

def letter_mapper(key): 
    return chr(key + 97)


def data_processing(data): 
    data_map = {}

    for row in data: 
        row = row.replace(" ", "")
        key = key_mapper(row[1])
        data_map[key] = row[3:]
    
    return data_map

def get_literals_from_string(s): 
    return [char for char in s if key_mapper(char) in range(26)]

def find_relations(data_map): 
    dependencies = set()
    independencies = set()

    for key1 in data_map.keys(): 
        for key2 in data_map.keys(): 
            if data_map[key1][0] in get_literals_from_string(data_map[key2]): 
                dependencies.add((letter_mapper(key1), letter_mapper(key2)))
                dependencies.add((letter_mapper(key2), letter_mapper(key1)))
            else: 
                if (letter_mapper(key2), letter_mapper(key1)) not in independencies:
                    independencies.add((letter_mapper(key1), letter_mapper(key2)))
            
    return dependencies, independencies


data = reading_data("data")
data_map = data_processing(data)
D, I = find_relations(data_map)
