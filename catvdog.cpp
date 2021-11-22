#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Person {
        public:
				int index;
                int keep;
                int leave;
        void setValues (int x, int y, int z) {
			index = x;
			keep = y;
			leave = z;
		}
};

map<bool, bool> map;
vector <int> matching;

int main() {
        // Defining variables and taking input
        int numTests;
        int cats;
        int dogs;
        int voters;
        vector <Person> people;

        cin >> numTests;

        for (int i=0; i < numTests; i ++){
                cin >> cats;
                cin >> dogs;
                cin >> voters;
				
				//reading votes
                for (int k = 0; k < voters; k++){
                        string inputString1;
                        string inputString2;
                        cin >> inputString1;
                        cin >> inputString2;
                        int keep = std::stoi(inputString1.at(0));
                        int leave = std::stoi(inputString2.at(0));
                        char animal = inputString1.at(0);

                        if (animal == "D") {
								Person person;
								person.setValues(k, -keep, leave);
                                people.add(people.begin(), person);
                        }
                        else {
								Person person;
								person.setValues(k, -keep, leave);
                                people.add(people.begin(), person);
                        }

                }
                
				// making map
                for (Person person1 : people) {
                        for (Person person2 : people) {
                                if ((person1.keep == person2.leave) && (person1 != person2)) {
									map.insert({person1.key, true});
									map.insert({person2.key, true});
								}
								else if (person2.keep == person1.leave && person1 != person2) {
									map.insert({person1.key, true});
									map.insert({person2.key, true});
								}
						}

                }
                
                for (int n = 0; n < voters; n++){
					matching[n] = -1;
					
					vector <bool> checked;
					int count = 0;
					for (int x = 0; x < voters; x++){
						if (people.get(x).keep < 0) {
							if (match(x, checked)){
								count++;
							}
						}
					}
				}
				
				cout << voters-count;

        }
}

void match(int p; vector <bool> checked) {
	for (int adj = 0; adj < voters; adj++){
		if ((adj = map.at(p)) && (adj != p)) {
			if (matching[adj] < 0){
				matching[adj] = p;
				return true
			}
		}
		
		if ((adj = map.at(p)) && !checked[adj] && (adj != p)){
			checked[adj] = true;
			if (match(matching[adj], checked)){
				matching[adj] = p;
				return true;
			}
		}
	}
	return false;
}
