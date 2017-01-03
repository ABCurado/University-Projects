int menor_data_em_C(int data1, int data2){
	int data1_novo_formato,data2_novo_formato;/*Poe o ano nos bytes mais significavos para ser possivel compara-los */
	data1_novo_formato = (unsigned int) data1;
	data2_novo_formato = (unsigned int) data2;
	data1_novo_formato = (data1 << 16) | (data1 >> 16);
	data2_novo_formato = (data2 << 16) | (data2 >> 16);
	if (data1_novo_formato < data2_novo_formato)
		return data1;
	else
		return data2;
}


