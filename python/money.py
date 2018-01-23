import random

init_gold, person, minutes = 100, 500, 10000
golds = [init_gold] * person

factor=100
me=[]

for m in range(minutes):
    if m % factor == 0:
        me.append(golds[0])
    for p in range(person):
        if golds[p] > 0:
            golds[p] -= 1
            choice_person = random.randint(0, person - 1)
            golds[choice_person] += 1

for i in golds:
    print i

print '-------------------------------------------------'
for i in me:
    print i
print 'len of me %d' % len(me)
