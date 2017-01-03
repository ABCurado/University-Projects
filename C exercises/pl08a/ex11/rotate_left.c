int rotate_left (int num, int nbits){
	return (num << nbits) | (num >> (32-nbits));
}

