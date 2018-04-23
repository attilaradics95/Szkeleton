#include <iostream>
#include <fstream>
#include <stdio.h>
#include <string.h>

using namespace std;

int main()
{
    string testFile;
    string expectedTestFile;
    cout << "Adja meg, hogy melyik teszt fajlt akarja osszehasonlitani (kiterjesztessel egyutt): ";
    cin >> testFile;
    cout << "Adja meg, hogy melyik elvart teszt fajllal akarja osszehasonlitani (kiterjesztessel egyutt): ";
    cin >> expectedTestFile;
    fstream test(testFile), expectedTest(expectedTestFile);
	char string1[256], string2[256];
	int j = 0;
	int faults = 0;

	while(!test.eof())
	{
		test.getline(string1, 256);
		expectedTest.getline(string2, 256);
		j++;
		if(strcmp(string1, string2) != 0)
		{
		    faults++;

			cout << j << ".sor nem egyezik" << "\n";
			cout << "\t   elvart: " << string2 << "\n";
			cout << "\ttenyleges: " << string1 << "\n";
		}
	}

	if(faults == 0) {
        cout << endl;
        cout << "A teszt hiba nelkul lefutott.";
        cout << endl;
	}
	else {
        cout << endl;
        cout << "Hibak szama: " << faults;
        cout << endl;
	}

    return 0;
}
