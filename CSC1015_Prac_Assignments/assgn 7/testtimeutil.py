"""
>>> import timeutil
>>> timeutil.validate(':45 p.m.')
False
>>> timeutil.validate('111:50 p.m.')
False
>>> timeutil.validate('01:45 a.m.')
False
>>> timeutil.validate('1:12 am')
False
>>> timeutil.validate('1:111 p.m.')
False
>>> timeutil.validate('1:86 a.m.')
False

"""
import doctest
doctest.testmod(verbose=True)
