"""
>>> import numberutil
>>> numberutil.aswords(0)
'zero'
>>> numberutil.aswords(10)
'ten'
>>> numberutil.aswords(106)
'one hundred and six'
>>> numberutil.aswords(70)
'seventy'
>>> numberutil.aswords(100)
'one hundred'
>>> numberutil.aswords(21)
'twenty one'
>>> numberutil.aswords(450)
'four hundred and fifty'
>>> numberutil.aswords(999)
'nine hundred and ninety nine'

"""
import doctest
doctest.testmod(verbose=True)

