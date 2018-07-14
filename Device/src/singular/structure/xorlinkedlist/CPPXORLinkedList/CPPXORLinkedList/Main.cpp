#include "XORLinkedList.h"
#include <iostream>
#include <limits>

using namespace std;

int main(int argc, char *argv[])
{
	XORLinkedList<int> xList;

	for (int i = 0; i < 10; ++i)
	{
		xList.push_front(i);
	}

	xList.display();

	cout << "Press enter to continue..." << endl;
	cin.ignore(numeric_limits<streamsize>::max(), '\n');
	return 0;
}