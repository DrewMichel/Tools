#ifndef XOR_LINKED_LIST_H
#define XOR_LINKED_LIST_H

#include <iostream>
// full header implementation because templates in cpp files are dumb
template<typename TData>
class XORLinkedList
{
	private:

		// Nested class start
		class XORNode
		{
			// Member variables
			public:
				XORNode *link;
				TData data;

				// Constructors
				XORNode()
				{
					link = nullptr;
				}

				XORNode(XORNode *linkIn, TData dataIn)
				{
					link = linkIn;
					data = dataIn;
				}

				// Destructor
				~XORNode()
				{
					//delete link;
				}
		};
		
		// Nested class finish

		// Member variables

		// head, tail - 1, tail
		XORNode *head, *tail;
		int elements;

		// Private member functions
		XORNode* exor(const XORNode *left, const XORNode *right) const
		{
			return (XORNode*)(((unsigned long)left) ^ ((unsigned long)right));
		}

	public:

		// Constructor
		XORLinkedList() : head(nullptr), tail(nullptr), elements(0)
		{
		}

		// Destructor
		~XORLinkedList()
		{
			/*
			XORNode *current = head, *next;
			head = nullptr;
			tail = nullptr;

			while (current != nullptr)
			{
				next = current->link;
				delete current;
				current = next;
			}
			*/
		}

		// Member functions

		// TODO: After push_front function supports xor
		void push_back(const TData& dataIn)
		{
			/*
			if (elements == 0)
			{
				head = new XORNode(nullptr, dataIn);
				tail = head;
				++elements;
			}
			else if (elements == 1)
			{
				tail = new XORNode(exor(head, nullptr), dataIn);
				head->link = exor(nullptr, tail);

				++elements;
			}
			else
			{
				XORNode *insert = new XORNode(exor(tail, nullptr), dataIn);
				tail->link = exor(tail.link, insert);

				tail->link = insert;

				++elements;
			}
			*/
		}

		void push_front(const TData& dataIn)
		{
			if (elements == 0)
			{
				head = new XORNode(nullptr, dataIn);
				tail = head;
				++elements;
			}
			else if (elements == 1)
			{
				head = new XORNode(exor(nullptr, tail), dataIn);
				tail->link = exor(head, nullptr);

				++elements;
			}
			else
			{
				// XOR insert -- fails
				/*
				XORNode *insert = new XORNode(exor(nullptr, head), dataIn);

				head->link = exor(insert, head->link);

				head = insert;

				++elements;
				*/

				// normal insert -- works
				XORNode *insert = new XORNode(head, dataIn);
				head = insert;
				++elements;
			}
		}

		void display()
		{
			XORNode *iterator = head;

			for (int i = 0; i < elements; ++i)
			{
				std::cout << iterator->data << " ";
				iterator = iterator->link;
			}
		}
			
};

#endif