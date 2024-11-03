import data, graph, util



if __name__ == "__main__":
    w, input_data = data.reading_data("data")
    w, data_map = data.data_processing(w, input_data)
    D, I = graph.find_relations(data_map)
    dieckert_graph = graph.dieckert_graph(D, w)
    FNF = graph.foata_form(dieckert_graph)

    print(f"D: {D}")
    print(f"I: {I}")
    print("GRAPH: ")


    graph.print_graph(dieckert_graph,w)
    
    FNF = util.map_indices_to_chars(FNF, w)

    print(f"FNF: {FNF}")

