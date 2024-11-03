def get_literals_from_string(s): 
    return [char for char in s if key_mapper(char) in range(26)]

def key_mapper(letter): 
    return ord(letter) - 97

def letter_mapper(key): 
    return chr(key + 97)

def map_indices_to_chars(list, w):
    result = []
    for sublist in list:
        chars = [w[i] for i in sublist]
        result.append(chars)
    return result