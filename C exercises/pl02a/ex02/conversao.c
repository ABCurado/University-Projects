
int toInt(char input[]) {
  int c, output = 0;
 
  for (c = 0; input[c] != 0; c++) {
    output = output * 10 + input[c] - '0';
  }
 
  return output;
}