int rotate_right (int num, int nbits){
	return (num >> nbits) | (num << (32-nbits));
}

